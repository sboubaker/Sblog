package jobs;

import play.jobs.Job;

import play.cache.Cache;
import play.jobs.OnApplicationStart;

/**
 * Created by IntelliJ IDEA.
 * User: boubaker
 * Date: 13/06/11
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
@OnApplicationStart
public class CacheCleaner extends Job{

    public void doJob() {
        Cache.clear();
    }
    public CacheCleaner(){

    }
}
