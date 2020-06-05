package me.jmix.brothertakeaway.utils;

import me.jmix.brothertakeaway.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("操作成功");
        return resultVO;
    }

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 表示异常的ResultVO
     *
     * @param exceptionClassName exceptionClassName
     * @param stateCode stateCode
     * @param errMsg errMsg
     * @return
     */
    public static ResultVO error(String exceptionClassName, Integer stateCode, String errMsg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setExceptionClassName(exceptionClassName);
        resultVO.setCode(stateCode);
        resultVO.setMsg(errMsg);
        return resultVO;
    }
}
