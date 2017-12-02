var React = require('react');

//子页面路由
var PageRoutes = React.createClass({
    getDefaultProps:function(){}
    ,componentDidMount:function() {}
    ,render:function() {
        return this.props.children;
    }
});
module.exports = PageRoutes;
