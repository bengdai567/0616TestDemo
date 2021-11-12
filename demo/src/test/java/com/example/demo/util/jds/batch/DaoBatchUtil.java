package com.example.demo.util.jds.batch;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

//用来批量存储时进行分批落库
public class DaoBatchUtil {
    private static final int SUBMIT_SIZE = 100;
    private static final int UPDATE_SIZE = 100;

    public DaoBatchUtil() {
    }

    public static int insertBatch(List dataList, IInsertBatchService iInsertBatchService) {
        if (CollectionUtils.isEmpty(dataList)) {
            return 0;
        } else {
            int listSize = dataList.size();
            int size = listSize / 100;
            int lastSize = 100;
            if (listSize % 100 != 0) {
                lastSize = listSize - size * 100;
                ++size;
            }

            int count = 0;

            for(int i = 0; i < size; ++i) {
                if (i == size - 1) {
                    count = (int)((long)count + iInsertBatchService.doBatch(dataList.subList(i * 100, i * 100 + lastSize)));
                } else {
                    count = (int)((long)count + iInsertBatchService.doBatch(dataList.subList(i * 100, i * 100 + 100)));
                }
            }

            return count;
        }
    }

    public static int updateBatchByNos(Object obj, List nosList, IUpdateBatchService iUpdateBatchService) {
        if (CollectionUtils.isEmpty(nosList)) {
            return 0;
        } else {
            List list = new ArrayList(nosList);
            int listSize = list.size();
            int size = listSize / 100;
            int lastSize = 100;
            if (listSize % 100 != 0) {
                lastSize = listSize - size * 100;
                ++size;
            }

            int count = 0;

            for(int i = 0; i < size; ++i) {
                if (i == size - 1) {
                    count = (int)((long)count + iUpdateBatchService.doBatch(obj, list.subList(i * 100, i * 100 + lastSize)));
                } else {
                    count = (int)((long)count + iUpdateBatchService.doBatch(obj, list.subList(i * 100, i * 100 + 100)));
                }
            }

            return count;
        }
    }

    public static int updateBatch(List objList, IUpdateBatchService iUpdateBatchService) {
        if (CollectionUtils.isEmpty(objList)) {
            return 0;
        } else {
            int listSize = objList.size();
            int size = listSize / 100;
            int lastSize = 100;
            if (listSize % 100 != 0) {
                lastSize = listSize - size * 100;
                ++size;
            }

            int count = 0;

            for(int i = 0; i < size; ++i) {
                if (i == size - 1) {
                    count = (int)((long)count + iUpdateBatchService.doBatch(objList.subList(i * 100, i * 100 + lastSize)));
                } else {
                    count = (int)((long)count + iUpdateBatchService.doBatch(objList.subList(i * 100, i * 100 + 100)));
                }
            }

            return count;
        }
    }
}
