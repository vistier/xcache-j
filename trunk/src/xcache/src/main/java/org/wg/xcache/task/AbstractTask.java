package org.wg.xcache.task;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 抽象任务
 * @author enychen Aug 9, 2009
 */
public abstract class AbstractTask extends TimerTask {

    /** log */
    private static final Log   log    = LogFactory.getLog(AbstractTask.class);

    /** 是否可以执行 */
    protected volatile boolean canRun = true;

    /*
     * (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @Override
    public void run() {
        if (this.canRun) {
            if (log.isInfoEnabled()) {
                log.info(this.getName() + "开始执行...");
            }

            try {
                this.doRun();
            } catch (Exception e) {
                log.error(this.getName() + "执行异常！", e);
            }

            if (log.isInfoEnabled()) {
                log.info(this.getName() + "执行完成。");
            }
        }
    }

    /**
     * 是否可以执行
     * @return 是否可以执行
     */
    public boolean isCanRun() {
        return canRun;
    }

    /**
     * 暂停
     */
    public void pause() {
        this.canRun = false;
    }

    /**
     * 继续
     */
    public void goon() {
        this.canRun = true;
    }

    /**
     * 获取任务名
     * @return 任务名
     */
    public abstract String getName();

    /**
     * 执行
     */
    protected abstract void doRun();

}
