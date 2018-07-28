package com.hzgc.common.collect.facesub;

import com.hzgc.common.util.json.JSONUtil;
import com.hzgc.common.zookeeper.Curator;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;

import java.io.Serializable;
import java.util.*;

public class FtpSubscribeClient implements Serializable {

    private static final Logger LOG = Logger.getLogger(FtpSubscribeClient.class);
    private static Map<String, List<String>> sessionMap = new HashMap<>();
    private final Object[] lock = new Object[0];
    private final String ftp_subscribe_path = "/ftp_subscribe";
    private Curator subscribeClient;


    public FtpSubscribeClient(String zkAddress){
        subscribeClient = new Curator(zkAddress, 1000, 6000);
        if (subscribeClient.nodePathExists(ftp_subscribe_path)){
            LOG.info("Ftp subscribe root path '"+ ftp_subscribe_path + "' is exists");
        } else {
            subscribeClient.createNode(ftp_subscribe_path, null, CreateMode.PERSISTENT);
            LOG.info("Ftp subscribe root path '"+ ftp_subscribe_path + "' create successfully");
        }
        initPathCache(subscribeClient.getClient());
    }

    /**
     * 创建/更新抓拍订阅用户
     * @param sessionId
     * @param ipcIdList
     */
    public void updateSessionPath(String sessionId, List<String> ipcIdList) {
        if (sessionId != null && sessionId.length() > 0
                && ipcIdList != null && ipcIdList.size() > 0){
            String nodePath = ftp_subscribe_path + "/" + sessionId;
            byte[] nodeData = JSONUtil.toJson(ipcIdList).getBytes();
            if (subscribeClient.nodePathExists(nodePath)){
                subscribeClient.setNodeDate(nodePath, nodeData);
                LOG.info("Update ftp subscribe child node path: " + nodePath
                        + " successfully, update data: " + JSONUtil.toJson(ipcIdList));
            }else {
                subscribeClient.createNode(nodePath, nodeData, CreateMode.PERSISTENT);
                LOG.info("Create ftp subscribe child node path: " + nodePath
                        + " successfully, data: " + JSONUtil.toJson(ipcIdList));
            }
        }
    }

    /**
     * 删除抓拍订阅用户
     * @param sessionId
     */
    public void deleteSessionPath(String sessionId) {
        String nodePath = ftp_subscribe_path + "/" + sessionId;
        if (subscribeClient.nodePathExists(nodePath)) {
            subscribeClient.deleteChildNode(nodePath);
            LOG.info("Delete ftp subscribe child node path: " + nodePath + " successful");
        }else {
            LOG.info("Delete ftp subscribe child node path: " + nodePath + " is not exists");
        }
    }

    private void initPathCache(CuratorFramework curatorFramework){
        final PathChildrenCache pathCache =
                new PathChildrenCache(curatorFramework, ftp_subscribe_path, true);
        try {
            pathCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            pathCache.getListenable().addListener((client, event) -> {
                String log = event.getData() != null ? event.getData().getPath() : null;
                LOG.info("Ftp subscribe child event [type:" + event.getType()
                        + ", path:" + log + "]");
                switch (event.getType()) {
                    case CHILD_ADDED:
                        refreshData(pathCache.getCurrentData());
                        break;
                    case CHILD_UPDATED:
                        refreshData(pathCache.getCurrentData());
                        break;
                    case CHILD_REMOVED:
                        refreshData(pathCache.getCurrentData());
                        break;
                    default:
                        break;
                }
            });
            //尝试第一次刷新节点下数据
            refreshData(pathCache.getCurrentData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshData(List<ChildData> currentData) {
        if (currentData != null && currentData.size() > 0){
            Map<String, List<String>> map = new HashMap<>();
            for (ChildData childData : currentData){
                String path = childData.getPath();
                String sessionId = path.replace(ftp_subscribe_path + "/", "");
                List<String> ipcIds = JSONUtil.toObject(new String(childData.getData()), List.class);
                map.put(sessionId, ipcIds);
            }
            synchronized (lock) {
                sessionMap = map;
                LOG.info("Ftp subscribe info:" + JSONUtil.toJson(sessionMap));
            }
        }
    }

    public static Map<String, List<String>> getSessionMap() {
        Map<String, List<String>> ipcMappingUser = new HashMap<>();
        sessionMap.keySet().forEach(sessionId -> {
            List<String> ipcList = sessionMap.get(sessionId);
            for (String ipc: ipcList) {
                if (!ipcMappingUser.containsKey(ipc)) {
                    List<String> userList = new ArrayList<>();
                    userList.add(sessionId);
                    ipcMappingUser.put(ipc, userList);
                } else {
                    List<String> userList = ipcMappingUser.get(ipc);
                    userList.add(sessionId);
                    ipcMappingUser.put(ipc, userList);
                }
            }
        });
        return ipcMappingUser;
    }
}
