import React, {
  Component,
} from 'react';
import {
  Image,
  requireNativeComponent,
  View,
} from 'react-native';


var NativeDrawView = requireNativeComponent('DrawView', DrawView);


export default class DrawView extends Component {
  constructor(props) {
    super(props);
    this._assignRoot = this._assignRoot.bind(this);
    this.setNativeProps = this.setNativeProps.bind(this);
  }
  render() {
    return <NativeDrawView
              ref={this._assignRoot}
              {...this.props}
           />;
  }

  _assignRoot(component) {
    this._root = component;
  }

  setNativeProps(nativeProps) {
    this._root.setNativeProps(nativeProps);
  }
}


DrawView.propTypes = {
  src: React.PropTypes.object,
  tileWidth: React.PropTypes.number,
  tileHeight: React.PropTypes.number,
  tiles: React.PropTypes.object,
  forceDraw: React.PropTypes.bool,
};
