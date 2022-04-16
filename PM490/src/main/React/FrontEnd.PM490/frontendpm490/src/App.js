import React, {Component} from "react";
import {Switch, Route, Link, BrowserRouter as Router} from "react-router-dom";
import axios from 'axios';
import jwt_decode from 'jwt-decode';

import CreateProduct from './components/CreateProduct';
//import Cart from './components/Cart';
import Login from './components/Login';
import ProductList from './components/ProductList';
import SignUp from './components/SignUp';
import Context from "./Context";
import SearchProducts from './components/SearchProducts';

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            cart: {},
            search:null,
            products: []
        };
        this.routerRef = React.createRef();
    }

    async componentDidMount() {
        let user = localStorage.getItem("user");
        let cart = localStorage.getItem("cart");
        let search = localStorage.getItem("search");
        if(search){

        }
        search = search ? await axios.get('http://localhost:8080/api/product') : null;
        const products = await axios.get('http://localhost:8080/api/product');
        user = user ? JSON.parse(user) : null;
        cart = cart ? JSON.parse(cart) : {};

        this.setState({user, search, products: products.data, cart});
    }

    search = async (sname, scolor, svendor, scategory) => {
        const res = await axios.post(
            'http://localhost:8080/api/product/advancesearch',
            {sname,scolor, svendor, scategory},
        ).catch((res) => {
            return {message:"No products found!"}
        })
    }

    login = async (username, password) => {
        const res = await axios.post(
            'http://localhost:8080/api/auth/signin',
            {username, password},
        ).catch((res) => {
            return {status: 401, message: 'Unauthorized'}
        })

        if(res.status === 200) {
            const { username } = jwt_decode(res.data.accessToken)
            //console.log("###################"+res.data.role);
            const user = {
                id: res.data.id,
                username : res.data.username,
                role: res.data.role,
                token: res.data.accessToken,
               // accessLevel: email === 'admin@example.com' ? 0 : 1
                // role: res.data.role === 'VENDOR' ? 0 : 1
                 //role: res.data.role === 'VENDOR' ? 0 : 1
            }

            this.setState({user});
            localStorage.setItem("user", JSON.stringify(user));
            console.log("###################"+user.token);
            return true;
        } else {
            return false;
        }

    }

    createProduct = (product, callback) => {
        /*
        let products = this.state.products.slice();
        products.push(product);
        this.setState({ products }, () => callback && callback());
         */
    };
    logout = e => {
        e.preventDefault();
        this.setState({user: null});
        localStorage.removeItem("user");
    };

    render() {
        return (
            <Context.Provider
                value={{
                    ...this.state,
                    //removeFromCart: this.removeFromCart,
                    //addToCart: this.addToCart,
                    login: this.login,
                    //signup: this.signUp,
                    createProduct: this.createProduct,
                    //clearCart: this.clearCart,
                    checkout: this.checkout
                }}
            >
                <Router ref={this.routerRef}>
                    <div className="App">
                        <nav
                            className="navbar container"
                            role="navigation"
                            aria-label="main navigation"
                        >
                            <div className="navbar-brand">
                                <b className="navbar-item is-size-4 ">ABC Branded Store</b>
                                <label
                                    role="button"
                                    class="navbar-burger burger"
                                    aria-label="menu"
                                    aria-expanded="false"
                                    data-target="navbarBasicExample"
                                    onClick={e => {
                                        e.preventDefault();
                                        this.setState({showMenu: !this.state.showMenu});
                                    }}
                                >
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                    <span aria-hidden="true"></span>
                                </label>
                            </div>
                            <div className={`navbar-menu ${
                                this.state.showMenu ? "is-active" : ""
                            }`}>
                                <Link to="/products" className="navbar-item">
                                    Products
                                </Link>
                                {this.state.user && this.state.user.role === "VENDOR" && (
                                    <Link to="/create-product" className="navbar-item">
                                        Create Product
                                    </Link>
                                )}
                                <Link to="/cart" className="navbar-item">
                                    ShoppingCart
                                    <span
                                        className="tag is-primary"
                                        style={{marginLeft: "5px"}}
                                    >
                                        {Object.keys(this.state.cart).length}
                                    </span>
                                </Link>
                                {!this.state.user ? (
                                    <Link to="/login" className="navbar-item">
                                        Login
                                    </Link>
                                ) : (
                                    <Link to="/" onClick={this.logout} className="navbar-item">
                                        Logout
                                    </Link>

                                )}

                            </div>
                        </nav>
                        <SearchProducts />
                        <Switch>
                            <Route exact path="/" component={ProductList} />
                            <Route exact path="/create-product" component={CreateProduct}/>
                            <Route exact path="/login" component={Login} />
                            <Route exact path="/signup" component={SignUp}/>
                            <Route exact path="/products" component={ProductList} />
                        </Switch>
                    </div>
                </Router>
            </Context.Provider>
        );
    }
}