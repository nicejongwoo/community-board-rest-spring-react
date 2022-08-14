import React from 'react';
import {Link} from "react-router-dom";
import {PrinterFill} from "react-bootstrap-icons";

const BreadcrumbComponent = ({title, path1, path2, name1, name2}) => {
    return (
        <div className="flex-root page-title-wrapper">
            <p className="page-title">{title}</p>
            <ul className="breadcrumb-wrapper">
                <li>
                    <Link to="/">HOME</Link>
                </li>

                {path1 && <li>
                    <Link to={`/${path1}`} >{name1}</Link>
                </li>}

                {path2 && <li>
                    <Link to={`/${path2}`} >{name2}</Link>
                </li>}
            </ul>
            <div className="print">
                <Link to="/print">
                    <PrinterFill />
                </Link>
            </div>
        </div>
    );
};

export default BreadcrumbComponent;
