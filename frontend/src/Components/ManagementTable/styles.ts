import styled from 'styled-components';
import { HiOutlineTrash } from "react-icons/hi";
import {HiPencilSquare} from "react-icons/hi2"

export const ToggleContainer = styled.div<{ isOn: boolean }>`
  width: 60px;
  height: 28px;
  border-radius: 17px;
  background-color: ${(props) => (props.isOn ? 'green' : '#ccc')} !important;
  position: relative;
  cursor: pointer;
`;

export const ToggleCircleOff = styled.div`
  width: 22px;
  height: 22px;
  border-radius: 11px;
  background-color: white;
  position: absolute;
  top: calc(50% - 11px);
  left: 4px;
  transition: .4s;
`;

export const ToggleCircleOn = styled(ToggleCircleOff)`
    left: calc(100% - 30px);
`;

export const ThrashIcon = styled(HiOutlineTrash)`
  color: #fff;
  font-size: 22px;
  margin-top: -5px;
  cursor: pointer;
`;

export const PencilIcon = styled(HiPencilSquare)`
  color: #fff;
  font-size: 22px;
  margin-top: -5px;
  cursor: pointer;
`;