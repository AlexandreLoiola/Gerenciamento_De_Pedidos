import styled from "styled-components";
import { HiUser } from "react-icons/hi";
import { FiLogOut } from "react-icons/fi";

export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 50px auto;
`;

export const TitleContainer = styled.div`
  display: flex;
  align-items: center;  
  width: 60%;
  flex-direction: column;
`;

export const LogoutIcon = styled(FiLogOut)`
  color: #000;
  font-size: 36px;
  margin-right: 5px;
  cursor: pointer;
  width: 100%;
`;

export const TitleText = styled.h1`
  font-size: 32px;
`;

export const UserIcon = styled(HiUser)`
  font-size: 60px;
  margin-left: 5px;
  width: 20%;
  cursor: pointer;
`;

export const Divider = styled.div`
  width: 20%;
  height: 5px;
  background-color: #317b22;
  margin: 0 auto;
`;

export const Container = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  height: 100%;
  width: 20%;
  align-items: center;

  p {
    color: #000;
    text-decoration: underline;
    margin-top: 0px;
    font-weight: 500;
    cursor: pointer;
  }
`;
