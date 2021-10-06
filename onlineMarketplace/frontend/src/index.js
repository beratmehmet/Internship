import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './container/App';
import reportWebVitals from './reportWebVitals';
import './bootstrap-override.scss';
import './i18n';
import {Provider} from 'react-redux';
import configureStore from './redux/configureStore';

const store=configureStore();

ReactDOM.render(
   <Provider store={store}>
     <App />,
   </Provider>,
  
  document.getElementById('root')
);
reportWebVitals();
