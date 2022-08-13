import React from 'react';
import "./css/left.css";
import {Link} from "react-router-dom";

const Left = () => {

    const temps = Array.from({length: 10}, () => "메뉴");

    return (
        <nav className="left-container content-root">
            <div className="left-content">
                <ul className="menu-wrapper">
                    {temps && temps.map((temp, index) => (<li key={index} className="menu-item">
                        <Link to={`/${index}`}>
                            <span className="menu-name">{temp}-{index}</span>
                        </Link>
                    </li>))}
                </ul>
            </div>

            <footer>
                <p>
                    test
                </p>
            </footer>
        </nav>
    );
};

export default Left;
