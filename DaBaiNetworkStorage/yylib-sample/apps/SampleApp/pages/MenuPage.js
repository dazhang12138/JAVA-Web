/**
 * 左侧菜单树
 *
 * Created by Dio on 2016/4/12.
 */
var React = require('react');
var {Link} = require('react-router');
var {YYPage,YYMenu,YYMenuItem,YYMenuSub,YYIcon} = require('yylib-ui');

var MenuPage = React.createClass({
    render: function () {
        return (
            <YYPage>
                <YYMenu theme="light" defaultOpenKeys={['sub_module1']} style={{width: 240}} mode="inline">
                    <YYMenuItem>发版时间：XXXX年XX月XX日</YYMenuItem>
                    <YYMenuSub key="sub_module1" title={<span><YYIcon type="book" /><span>本应用工程</span></span>}>
                        <YYMenuItem key="1"><Link to="/sample"><span><YYIcon type="hdd"/>基础示例一</span></Link></YYMenuItem>
                        <YYMenuItem key="2"><Link to="/DB"><span><YYIcon type="poweroff"/>大白网盘</span></Link></YYMenuItem>
                    </YYMenuSub>
                </YYMenu>
            </YYPage>
        );
    }
});
module.exports = MenuPage;
