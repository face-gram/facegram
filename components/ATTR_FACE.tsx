const ATTR_FACE = [
  {
    level: 1,
    id: "type",
    name: "유형",
    buttons: ["역삼각형", "계란형", "긴형", "둥근형", "사각형", "마름모형"],
  },
  {
    level: 1,
    id: "size",
    name: "크기",
    buttons: ["크다", "보통", "작다"],
  },
  {
    level: 1,
    id: "foreheadType",
    name: "이마유형",
    buttons: ["알수없음", "둥근형", "일자형", "M자형", "각진형(사각형)", "화산형(사다리꼴)"],
  },
  {
    level: 2,
    id: "foreheadSize",
    name: "이마크기",
    buttons: ["알수없음", "넓다", "보통", "좁다"],
  },
  {
    level: 2,
    id: "chinType",
    name: "턱유형",
    buttons: ["분류없음", "둥근형", "V자형", "각진형(사각)", "이중형", "주걱형"],
  },
  {
    level: 3,
    id: "chinSize",
    name: "턱크기",
    buttons: ["분류없음", "길다", "짧다", "보통"],
  },
  {
    level: 3,
    id: "cheek",
    name: "볼",
    buttons: ["분류없음", "많음", "적음"],
  },
  {
    level: 0,
    id: "description",
    name: "주요설명",
    buttons: [],
  },
];

export default ATTR_FACE;

