import React, {Component} from "react";
import withContext from "../withContext";

class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sname: "",
            scolor: "",
            svendor: 0,
            scategory: 0
        };
        this.routerRef = React.createRef();
    }

    handleChange = e => this.setState({[e.target.name]: e.target.value, error: ""});

    search = (e) => {
        e.preventDefault();
        const {sname, scolor, svendor, scategory} = this.state;
        if (sname) {
            this.props.context.search(sname, scolor, svendor, scategory)
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
                            name="sname"
                            placeholder="Search products"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="scolor"
                            placeholder="Color"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="svendor"
                            placeholder="Vendor"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p className="control ">
                        <input
                            className="input"
                            type="text"
                            name="scategory"
                            placeholder="Category"
                            onChange={this.handleChange}
                            value={this.state.search}
                        />
                    </p>
                    <p class="control">
                        <a class="button" onClick={this.handleChange}>
                            🔎
                        </a>
                    </p>
                </div>
            </form>
        );
    }
}

export default withContext(Search);