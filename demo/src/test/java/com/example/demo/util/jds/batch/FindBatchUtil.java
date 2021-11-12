package com.example.demo.util.jds.batch;

import java.util.ArrayList;
import java.util.List;

//分批查询在组合
public class FindBatchUtil {
    private static final int FIND_SIZE = 100;

    public FindBatchUtil() {
    }

    public static List findBatch(IFindBatchService iFindBatchService, Object queryDto, List inNos) {
        if (inNos == null) {
            return iFindBatchService.doBatch(queryDto, (List)null);
        } else {
            List dataList = new ArrayList();
            if (inNos != null && inNos.size() == 0) {
                return dataList;
            } else {
                List list = new ArrayList(inNos);
                int listSize = list.size();
                int size = listSize / 100;
                int lastSize = 100;
                if (listSize % 100 != 0) {
                    lastSize = listSize - size * 100;
                    ++size;
                }

                for(int i = 0; i < size; ++i) {
                    if (i == size - 1) {
                        dataList.addAll(iFindBatchService.doBatch(queryDto, list.subList(i * 100, i * 100 + lastSize)));
                    } else {
                        dataList.addAll(iFindBatchService.doBatch(queryDto, list.subList(i * 100, i * 100 + 100)));
                    }
                }

                return dataList;
            }
        }
    }
}
