import styled from 'styled-components';

export const ToggleContainer = styled.div<{ isOn: boolean }>`
  width: 60px;
  height: 28px;
  border-radius: 17px;
  background-color: ${(props) => (props.isOn ? 'limegreen' : '#ccc')} !important;
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
  transition: left 0.8s ease;
`;

export const ToggleCircleOn = styled(ToggleCircleOff)`
    left: calc(100% - 30px);
`;