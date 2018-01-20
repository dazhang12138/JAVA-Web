/**
 * Created by Administrator on 2016/4/25.
 */
window.EnvConfig = window.EnvConfig || {}
var ADDR,ROOT_PATH,REF_SERVER_URL,URL_HOME_PORTAL,URL_WORKBENCH
ADDR = EnvConfig.ADDR;
ROOT_PATH = EnvConfig.ROOT_PATH;
// Portal首页地址
URL_HOME_PORTAL = EnvConfig.URL_HOME_PORTAL;
URL_WORKBENCH = EnvConfig.URL_WORKBENCH;
REF_SERVER_URL = EnvConfig.REF_SERVER_URL;

var URL_HOME = '/'; //主页的链接
// 单据类型（示例）
var MODULE_URL = {
    SAMPLE: ADDR + ROOT_PATH + "/sample"
};

module.exports = {
    URL_HOME,
    URL_HOME_PORTAL,
    REF_SERVER_URL,
    MODULE_URL,
    ADDR,
    ROOT_PATH
};
