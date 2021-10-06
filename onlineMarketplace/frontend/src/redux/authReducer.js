import * as actions from './Constants';
const defaultState = {
  isLoggedIn: false,
  username: undefined,
  fullName: undefined,
  image: undefined,
  password: undefined,
  isAdmin: false
}
const authReducer = (state = { ...defaultState }, action) => {
  if (action.type === actions.LOGOUT_SUCCESS) {
    return defaultState;
  } else if (action.type === actions.LOGIN_SUCCESS) {
    if (action.payLoad.role.some(e => e.name === 'Role_Admin')) {
      return {
        ...action.payLoad,
        isLoggedIn: true,
        isAdmin: true
      }
    }else {
      return {
        ...action.payLoad,
        isLoggedIn: true,
        isAdmin:false
      }
    }

  } else if (action.type === actions.UPDATE_SUCCESS) {
    return {
      ...state,
      ...action.payLoad
    }
  }
  return state;
}

export default authReducer;