import React, {
  Component,
} from 'react';
import {
  requireNativeComponent,
} from 'react-native';


const NativeWhiteBoardView = requireNativeComponent('WhiteBoardView', WhiteBoardView);


export default class WhiteBoardView extends Component {
  render() {
    return <NativeWhiteBoardView {...this.props} />;
  }
}


WhiteBoardView.propTypes = {
  milsecond: React.PropTypes.number,
  points: React.PropTypes.array,
};
