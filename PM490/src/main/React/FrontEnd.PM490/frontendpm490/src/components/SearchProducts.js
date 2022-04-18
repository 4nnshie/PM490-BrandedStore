import React, {Component} from "react";
import withContext from "../withContext";

class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            color: "",
            idVendor: 0,
            idCategory: 0
        };
        this.routerRef = React.createRef();
    }

    handleChange = e => this.setState({[e.target.name]: e.target.value, error: ""});

    search = (e) => {
        e.preventDefault();
        const {name, color, idVendor, idCategory} = this.state;
        if (name) {
            this.props.context.search(name, color, idVendor, idCategory)
        }
    };

    render() {
        return (
            <form onSubmit={this.search}>
                <div class="field has-addons ">
                    <p class="control is-expanded">
                        <input
                            class="input"
                            type="text"
                            name="name"
                            placeholder="Search products"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="color"
                            placeholder="Color"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="idVendor"
                            placeholder="Vendor"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="idCategory"
                            placeholder="Category"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p class="control">
                        <button class="button" >
                            🔎
                        </button>
                    </p>
                </div>
            </form>
        );
    }
}

export default withContext(Search);