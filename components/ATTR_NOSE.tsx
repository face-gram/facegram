const ATTR_NOSE = [
  {
    level: 1,
    id: "size",
    name: "크기",
    buttons: ["크다", "보통", "작다"],
  },
  {
    level: 1,
    id: "length",
    name: "길이",
    buttons: ["길다", "보통", "짧다"],
  },
  {
    level: 2,
    id: "height",
    name: "콧대",
    buttons: ["높다", "보통", "낮다"],
  },
  {
    level: 2,
    id: "top",
    name: "코끝모양(콧망울)",
    buttons: ["분류없음", "각진형", "둥근형", "뭉툭형", "메부리코", "들창코", "주먹코", "흰코", "버선코", "화살코"],
  },
  {
    level: 2,
    id: "nostrils",
    name: "콧볼넓이(콧날개)",
    buttons: ["분류없음", "넓다", "보통", "좁다"],
  },
  {
    level: 3,
    id: "philtrum",
    name: "코밑길이(인중길이)",
    buttons: ["길다", "보통", "짧다"],
  },
  {
    level: 0,
    id: "description",
    name: "주요설명",
    buttons: [],
  },
];

export default ATTR_NOSE;

