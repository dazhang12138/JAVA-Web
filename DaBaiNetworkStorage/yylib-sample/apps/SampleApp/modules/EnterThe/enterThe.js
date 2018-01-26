var React = require('react');
var _ = require('lodash');
var {YYClass} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
//页面初始化
var EventHanlder = {
    "P005164":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
        }
        ,onViewWillUpdate:function(options){
            console.log('page onViewWillUpdate',options);
        }
        ,onViewDidUpdate:function(options){
            console.log('page onViewDidUpdate',options);
        }
    }
}
var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005164" uiEvent={EventHanlder}/>
    }
});
module.exports = SimplePage;