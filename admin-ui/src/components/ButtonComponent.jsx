import React from "react";
import styled from "styled-components";

const StyledFlexRoot = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
`

const StyledButtonWrapper = styled(StyledFlexRoot)`
  width: 100%;
  justify-content: end;
  text-align: right;
  margin-bottom: 1em;
  li {
    margin: 0 8px;
    &:last-child {
      margin: 0;
    }
  }
`

const StyledButton = styled.button`
  //background-color: transparent;
  background-color: #2f315e;
  color: #ffffff;
  padding: .8em;
  border-radius: 5px;
  cursor: pointer;
  //border: 1px solid rgb(170, 170, 170);
  min-width: 40px;
  box-shadow: 0px 2px 1px -1px rgb(0 0 0 / 20%), 0px 1px 1px 0px rgb(0 0 0 / 14%), 0px 1px 3px 0px rgb(0 0 0 / 12%);
  line-height: 1.2;
  font-size: .9em;
  border: 0;

  &:hover {
    //background-color: rgb(170, 170, 170);
    background-color: #3e407a;
  }
`

export const AddButton = ({moveInsertPage}) => {
    return (
        <StyledFlexRoot className="flex-root">
            <StyledButtonWrapper as="ul">
                <li>
                    <StyledButton
                        type="button"
                        onClick={moveInsertPage}
                    >
                        등록
                    </StyledButton>
                </li>
            </StyledButtonWrapper>
        </StyledFlexRoot>
    );
};