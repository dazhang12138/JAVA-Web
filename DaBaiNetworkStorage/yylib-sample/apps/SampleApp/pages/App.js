var React = require('react');
var {connect} = require('react-redux');

var {YYApp,YYSpin} = require('yylib-ui');
require('./index.css');

var AuthToken = require("yylib-utils/AuthToken");

var App = React.createClass({
    getInitialState() {
        return {};
    },
    getDefaultProps: function () {
        return {}
    }, componentDidMount: function () {
    }
    , render: function () {
        return (
            <YYApp>
                {this.props.children}
            </YYApp>
        );
    }
});


module.exports = App;