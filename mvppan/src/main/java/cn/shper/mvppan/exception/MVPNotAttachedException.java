package cn.shper.mvppan.exception;

/**
 * Author: Shper
 * Description: 未绑定时异常
 * Version: V0.1 2016/12/28
 */
public class MVPNotAttachedException extends RuntimeException {

    public MVPNotAttachedException(String errMsg) {
        super(errMsg);
    }

}
