package jobs;

import play.jobs.Job;

import play.cache.Cache;
import play.jobs.OnApplicationStart;

/**
 * User: boubaker
 * Date: 13/06/11
 * Time: 15:29
 */
@OnApplicationStart
public class CacheCleaner extends Job{

    public void doJob() {
        Cache.clear();
    }
    public CacheCleaner(){

    }
}
