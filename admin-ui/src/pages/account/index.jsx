import React, {useEffect, useState} from 'react';
import BreadcrumbComponent from "components/BreadcrumbComponent";
import {ACCOUNT_MENU_NAME, ACCOUNT_PARAM} from "util/constant";
import {AddButton} from "components/ButtonComponent";
import SearchComponent from "components/SearchComponent";
import {ACCOUNT_TYPE_OPTIONS} from "util/constant/options";
import {StyledSection, StyledTotalCount} from "App";
import {TableComponent} from "components/TableComponent";
import PaginationComponent from "components/PaginationComponent";
import {useLocation, useNavigate} from "react-router-dom";
import {useRecoilState, useResetRecoilState, useSetRecoilState} from "recoil";
import {totalElementState} from "state/SearchState";
import {currentMenuState} from "state/menuState";
import AccountService from "service/account/accountService";

const Account = () => {

    const [contents, setContents] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);

    const setCurrentMenu = useSetRecoilState(currentMenuState);
    const resetCurrentMenu = useResetRecoilState(currentMenuState);

    const [loadingContents, setLoadingContents] = useState(false);

    const search = (parameters) => {
        AccountService.search(parameters).then(response => {
            console.log(response.data);
            setContents(response.data.content);
            setTotalElements(response.data.totalElements);
            setLoadingContents(false);
        }).catch(error => {
            console.error("error:: ", error);
        })
    }

    useEffect(() => {
        setCurrentMenu("account");
        setLoadingContents(true);
        search(location.search);
        return () => {
            resetCurrentMenu();
        }
    }, [location]);

    const columnNames = ["번호", "이름", "이메일", "휴대폰번호", "권한", "등록일"];
    let renderContents;
    if (contents.length > 0) {
        renderContents = contents.map((element, index) =>
            <tr
                key={index}
                onClick={(e) => {
                    navigate(`/test/${element.id}${location.search}`);
                }}
            >
                <td>{element.rowNum}</td>
                <td>{element.name}</td>
                <td>{element.email}</td>
                <td>{element.phone}</td>
                <td>
                    {element.roles && element.roles.map((role, index) =>
                        `${role.name}${index !== (element.roles.length - 1) ? ", " : ""}`
                    )}
                </td>
                <td>{element.joinedAt}</td>
            </tr>
        );
    } else {
        renderContents = <tr>
            <td colSpan={columnNames.length}>게시글이 없습니다.</td>
        </tr>
    }
    
    return (
        <StyledSection className="section">

            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={ACCOUNT_MENU_NAME}
                    name1={ACCOUNT_MENU_NAME}
                    path1={`/account${ACCOUNT_PARAM}`}
                />

                <AddButton moveInsertPage={() => {navigate(`/test/insert${location.search}`);}}/>

                <SearchComponent
                    page="account"
                    url="/account"
                    typeOptions={ACCOUNT_TYPE_OPTIONS}
                />

                <StyledTotalCount>검색: {totalElements} 건</StyledTotalCount>

                {loadingContents ? "게시글 로딩중..." : <TableComponent columnNames={columnNames} renderContents={renderContents} />}

                {!loadingContents && <PaginationComponent/>}

            </div>

        </StyledSection>
    );
};

export default Account;
