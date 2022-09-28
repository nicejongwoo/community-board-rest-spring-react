import React from 'react';
import {Link} from "react-router-dom";
import {PrinterFill} from "react-bootstrap-icons";
import styled from "styled-components";

const PageTitleWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  padding-bottom: .25rem;
  box-sizing: border-box;
  border-bottom: 1px solid #ccc;
  margin-bottom: 1em;
`

const StyledTitle = styled.p`
  flex: 1 1 auto;
  font-size: 32px;
  font-weight: bold;
`

const BreadCrumbWrapper = styled.ul`
  display: flex;
  flex: 1 1 auto;
  align-items: end;
  justify-content: end;
  list-style: none;
  li {
    display: inline;
    a:hover {
      text-decoration: underline;
    }
  }
  li+li:before {
    padding: 8px;
    content: "\\3009"
  }
`

const StyledPrint = styled.div`
  padding-left: 10px;
  display: flex;
  align-items: end;
  justify-content: end;
`

const BreadcrumbComponent = ({title, path1, path2, name1, name2}) => {

    return (
        <PageTitleWrapper>
            <StyledTitle className="page-title">{title}</StyledTitle>
            <BreadCrumbWrapper>
                <li>
                    <Link to="/">HOME</Link>
                </li>

                {path1 && <li>
                    <Link to={path1} >{name1}</Link>
                </li>}

                {path2 && <li>
                    <Link to={path2} >{name2}</Link>
                </li>}
            </BreadCrumbWrapper>
            <StyledPrint className="print">
                <Link
                    to="#"
                    onClick={(e) => {
                        e.preventDefault();
                        window.print();
                    }}
                >
                    <PrinterFill />
                </Link>
            </StyledPrint>
        </PageTitleWrapper>
    );
};

export default BreadcrumbComponent;
