//./src/components/ShoppingCart.js
import React, {Component} from "react";

import withContext from "../withContext";
import CartItem from "./CartItem";
import axios from "axios";

const initState = {
    subtotal: 0,
    tax: 0,
    total: 0,
    orderId: 0,
};

class ShoppingCart extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
        this.routerRef = React.createRef();
    }

    componentDidMount() {
        const {cart} = this.props.context;
        const cartKeys = Object.keys(cart || {});
        let subt = 0;
        cartKeys.forEach(key => {
            subt += (cart[key].product.price * cart[key].amount)
        });
        let tx = subt * 0.07;
        let tot = subt + tx;
        this.setState({subtotal: subt})
        this.setState({tax: tx.toFixed(2)});
        this.setState({total: tot});

        //console.log("@@@@@@" + this.subtotal);
        return cart;
    }

    checkout = async () => {
        const {user} = this.props.context;
        if (user) {
            const {cart} = this.props.context;
            const cartKeys = Object.keys(cart || {});
            const {user} = this.props.context;

            let res =  axios.all([
                cartKeys.forEach(key => {
                    axios.post(
                        'http://localhost:8080/api/shoppingcart/add',
                        {idProduct: cart[key].product.id, quantity: cart[key].amount},
                        {
                            headers: {
                                'Content-Type': 'application/json',
                                'Authorization': 'Bearer ' + user.token
                            }
                        },
                    ).catch((res) => {
                        return {status: res.status, message: res.message}
                    })
                })]);
            //console.log("### itemlist done" + res[cartKeys.length].status);
            if (res) {
                await this.createorder();
                window.location.replace("/PaymentMethod");

            }

            //window.location.replace("/PaymentMethod");
            return;
        } else if (!user) {
            window.location.replace("/login");
            return;
        }//with out login checkout will redirect to login page

        const cart = this.state.cart;

        const products = this.state.products.map(p => {
            if (cart[p.name]) {
                p.stock = p.stock - cart[p.name].amount;

                axios.put(
                    `http://localhost:8080/api/product/${p.id}`,
                    {...p},
                )
            }
            return p;
        });

        this.setState({products});
        this.clearCart();
    };

    createorder = async () => {
        const {user} = this.props.context;
        let today = new Date();
        let cDay = today.getDate()
        let cMonth = today.getMonth() + 1
        let cYear = today.getFullYear()
        let todayDate = new Date().toISOString().slice(0, 10);
        //let dateOrdered = cYear+'-'+cMonth+'-'+cDay;
        //let dateShipped = dateOrdered.setDate(dateOrdered.getDate() + 3);
        const res = await axios.post(
            'http://localhost:8080/api/order/create',
            {dateOrdered: todayDate, dateShipped: todayDate},
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + user.token
                }
            },
        ).then(r => this.setState(r))
        console.log("### order done");
/*
        if (res.status === 201) {
            this.setState({orderId: res.data.id});
            console.log("!!!" + this.state.orderId);
        }
*/
    }

    render() {
        const {cart} = this.props.context;
        const cartKeys = Object.keys(cart || {});
        //console.log("######" + cartKeys);
        //console.log("@@@@@@" + cart[3].product.price);
        let subtotal = 0;
        return (
            <>
                <div className="hero is-primary">
                    <div className="hero-body container">
                        <h4 className="title"> My Cart </h4>
                    </div>
                </div>
                <br/>
                <div className="container">
                    {cartKeys.length ? (
                        <div className="column columns is-multiline">
                            {
                                cartKeys.map(key => (

                                    //this.setState({subtotal:cart.item.amount});

                                    <CartItem
                                        cartKey={key}
                                        key={key}
                                        cartItem={cart[key]}
                                        removeFromCart={this.props.context.removeFromCart}

                                    />

                                ))}
                            <div className="column is-12is-clearfix">
                                <br/>
                                <div className="is-pulled-right">
                                    <div className="container is-bordered">

                                        <span>Subtotal = </span><small>{this.state.subtotal}</small>
                                        <br/>
                                        <span>Tax = </span><small>{this.state.tax}</small>
                                        <br/>
                                        <span>Total = </span><small>{this.state.total}</small>
                                    </div>
                                    <button
                                        onClick={this.props.context.clearCart}
                                        className="button is-warning"
                                    >
                                        Clear cart
                                    </button>
                                    {""}
                                    <button
                                        className="button is-success"
                                        onClick={this.checkout}
                                    >
                                        Checkout
                                    </button>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <div className="column">
                            <div className="title has-text-grey-light">No item in cart!</div>
                        </div>
                    )}
                </div>
            </>
        );
    };
}

export default withContext(ShoppingCart);
