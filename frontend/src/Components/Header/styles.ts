import styled from "styled-components";
import { HiUser } from "react-icons/hi";

export const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 60px auto ;
`;

export const TitleContainer = styled.div`
  display: flex;
  align-items: center;
  margin: 0 10px;
  width: 60%;
  flex-direction: column;
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