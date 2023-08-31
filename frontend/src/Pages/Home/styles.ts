import styled from 'styled-components';
import { Button } from 'react-bootstrap';
import {MdOutlineKeyboardArrowRight} from "react-icons/md"

export const StyledButton = styled(Button)`
    font-weight: 600;
    padding: 12px;
    padding-left: 30px;
    margin-bottom: 10px;
    text-align: left;
    position: relative;
    border: 1px solid;
`

export const ArrowRightIcon = styled(MdOutlineKeyboardArrowRight)`
    position: absolute;
    right: 20px;
    font-size: 32px;
`