import { HiOutlineLogout } from "react-icons/hi";
import styled from "styled-components";

export const LogoutIcon = styled(HiOutlineLogout)`
  color: #fff;
  font-size: 60px;
  margin-right: 5px;
  width: 98%;
`;

export const Container = styled.div`
    display: flex;
    justify-content: center;
    flex-direction: column;
    height: 100%;
    width: 30%;
    cursor: pointer;

    p {
      color: #fff;
      text-decoration: underline;
      margin-top: 0px;
      margin-left: 42%;
      font-weight: 500;
    }
    
`;
