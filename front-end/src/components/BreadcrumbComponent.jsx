import React from 'react';
import {Link} from "react-router-dom";
import {PrinterFill} from "react-bootstrap-icons";
import {useRecoilValue} from "recoil";
import {sizeState, sortState} from "../state/SearchState";
import "./css/breadcrumbComponent.css";

const BreadcrumbComponent = ({title, path1, path2, name1, name2}) => {

    const size = useRecoilValue(sizeState);
    const sort = useRecoilValue(sortState);

    return (
        <div className="flex-root page-title-wrapper">
            <p className="page-title">{title}</p>
            <ul className="breadcrumb-wrapper">
                <li>
                    <Link to="/">HOME</Link>
                </li>

                {path1 && <li>
                    <Link to={`/${path1}?page=0&size=${size}&sort=${sort}`} >{name1}</Link>
                </li>}

                {path2 && <li>
                    <Link to={`/${path2}?page=0&size=${size}&sort=${sort}`} >{name2}</Link>
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
