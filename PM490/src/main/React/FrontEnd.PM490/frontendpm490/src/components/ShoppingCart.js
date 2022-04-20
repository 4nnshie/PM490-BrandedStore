//./src/components/ShoppingCart.js
import React, { Component } from "react";
import React from "react";
import withContext from "../withContext";
import CartItem from "./CartItem";

const initState = {
    subtotal: 0,
    tax: 0,
    total: 0
};

class ShoppingCart extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
    }

    componentDidMount() {
        const {cart} = this.props.context;
        const cartKeys = Object.keys(cart || {});
        let subt = 0;
        cartKeys.forEach(key => {subt += (cart[key].product.price * cart[key].amount)} );
        let tx = subt* 0.07;
        let tot = subt+ tx;
        this.setState({subtotal:subt})
        this.setState({tax : tx.toFixed(2)});
        this.setState({total : tot});

        console.log("@@@@@@" + this.subtotal);
        return cart;
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
                                        onClick={this.props.context.checkout}
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
