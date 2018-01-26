/**
 * 路由配置
 */
var React = require('react');
var {Route, IndexRoute, Redirect} = require('react-router');

var App = require('../pages/App');//主应用
var MenuPage = require('../pages/MenuPage');//主菜单
// var PageRoutes = require('./PageRoutes');//页面路由
var lazy = require('yylib-utils/asyncLoader');//异步加载
var LazyPageRoutes = lazy(require('bundle?lazy!./PageRoutes'));//页面路由-延迟加载

//示例一
var SampleTable = lazy(require('bundle?lazy&name=example1!../modules/sample/SampleTable'));
var SampleForm = lazy(require('bundle?lazy&name=example1!../modules/sample/SampleForm'));

//main
var mainPage = lazy(require('bundle?lazy&name=example1!../modules/main/mainPage'));

//message
var userMessage = lazy(require('bundle?lazy&name=example1!../modules/message/userMessage'));

//EnterThe
var enterThe = lazy(require('bundle?lazy&name=example1!../modules/EnterThe/enterThe'));

module.exports = (
    <Route path="/" component={App}>
        <IndexRoute component={MenuPage}/>
        <Route path="/portal" component={App}>
        </Route>
        <Route path="sample" component={LazyPageRoutes}>
            <IndexRoute component={SampleTable}/>
            <Route path="form" component={SampleForm}/>
        </Route>
        <Route path="DB" component={LazyPageRoutes}>
            <IndexRoute component={enterThe}/>
            <Route path="mainPage" component={mainPage}/>
            <Route path='userMessage' component={userMessage}/>
        </Route>
    </Route>
);
