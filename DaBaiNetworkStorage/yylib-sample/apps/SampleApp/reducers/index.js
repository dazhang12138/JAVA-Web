/**
 * Created by Dio on 2016/4/10.
 */
var {combineReducers} = require('redux');
//注册路由组件需要的reducer
var { routerReducer }  = require('react-router-redux');
//注册参照组件需要的reducer
var refinfo = require('yylib-ui/refer/reducers/refinfo');
var ReduxUtils = require('yylib-utils/ReduxUtils');

var SampleAction = require('../modules/sample/SampleAction');
/**
 * 将所有State组织成一个状态树来进行维护
 */
export default combineReducers({
    routing: routerReducer,
    refinfo,
    sample: ReduxUtils.listen(SampleAction)
});