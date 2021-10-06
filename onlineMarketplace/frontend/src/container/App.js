import React from "react";
import LoginPage from "../pages/LoginPage";
import UserSignupPage from "../pages/UserSignupPage";
import LanguageSelector from "../components/LanguageSelector";
import HomePage from "../pages/HomePage";
import UserPage from "../pages/UserPage";
import AllUsers from "../pages/AllUsers";
import ProductsPage from "../pages/ProductsPage";
import ProductPage from "../pages/ProductPage";
import ProductSubmit from "../components/ProductSubmit";
import {Route, Redirect, Switch, HashRouter as Router} from 'react-router-dom';
import TopBar from "../components/TopBar";
import {useSelector} from 'react-redux';
import SellerListPage from '../pages/SellerListPage';
import SellerPage from "../pages/SellerPage";
import SellerSubmit from "../pages/SellerSubmit";
import {toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
toast.configure();
const App = (props)=> {
  const {isLoggedIn}= useSelector((store)=>({
    isLoggedIn:store.isLoggedIn
  }))
    return (
    
      <div>
        <Router>
          <TopBar />
          <Switch>
            <Route exact path="/"component={HomePage}></Route>
            {!isLoggedIn && <Route path="/login"component={LoginPage}></Route>}
            <Route path="/signup"component={UserSignupPage}></Route>
            <Route path="/user/:username"component={UserPage}></Route>
            <Route path="/users"component={AllUsers}></Route>
            <Route path="/products"component={ProductsPage}></Route>
            <Route path="/product/:id"component={ProductPage}></Route>
            <Route path="/sellers"component={SellerListPage}></Route>
            <Route path="/addproduct"component={ProductSubmit}></Route>
            <Route path="/seller/:id"component={SellerPage}></Route>
            <Route path="/addseller"component={SellerSubmit}></Route>
            <Redirect to="/" />
          </Switch>
        </Router>
        <LanguageSelector/>
      </div>   
  ); 
}

export default (App);
