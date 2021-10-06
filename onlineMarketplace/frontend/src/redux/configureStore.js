import { createStore, applyMiddleware,compose } from 'redux';
import thunk from 'redux-thunk';
import authReducer from './authReducer';
import SecureLS from 'secure-ls';
import { setAuthorizationHeader } from '../api/apiCalls';

  const secureLs=new SecureLS();
  const getStateFromStorage = ()=>{

    const productAuth=secureLs.get('product-auth');

    let stateInLocalStorage={
        isLoggedIn: false,
        username:undefined,
        fullName: undefined,
        image: undefined,
        password: undefined
      };

    if(productAuth){
        return productAuth;
    }

    return stateInLocalStorage;

  }

  const updateStateInStorage= newState=>{
    secureLs.set('product-auth',newState);
  }
  const configureStore= ()=>{
    const initialState=getStateFromStorage();
    setAuthorizationHeader(initialState);
    const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
    const store= createStore(authReducer, initialState,composeEnhancers(applyMiddleware(thunk)));
    
    store.subscribe(()=>{
        updateStateInStorage(store.getState());
        setAuthorizationHeader(store.getState());
    })
    return store;
 }

  export default configureStore;