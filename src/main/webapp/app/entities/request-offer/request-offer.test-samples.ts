import dayjs from 'dayjs/esm';

import { IRequestOffer, NewRequestOffer } from './request-offer.model';

export const sampleWithRequiredData: IRequestOffer = {
  id: 15441,
  offerValue: 31781,
  requestAmt: 18723,
  tradeValue: 22224,
  marginPtg: 12691.22,
  marginValue: 13784,
  amountAftMargin: 29239,
  interestPtg: 1566.74,
  term: 32498,
  interestValue: 18552.84,
  netAmount: 23333,
  status: 'bruit exemplify incidentally',
  financeRequestDate: dayjs('2024-03-20'),
  anchorTraderName: 'mmm design next',
  tradePartnerName: 'spectrograph',
};

export const sampleWithPartialData: IRequestOffer = {
  id: 19269,
  offerValue: 6100,
  requestAmt: 27630,
  tradeValue: 22310,
  marginPtg: 29789.57,
  marginValue: 27727,
  amountAftMargin: 1620,
  interestPtg: 28450.2,
  term: 28860,
  interestValue: 17506.47,
  netAmount: 4599,
  status: 'some ideal trawl',
  financeRequestDate: dayjs('2024-03-20'),
  anchorTraderName: 'ouch secret',
  tradePartnerName: 'ha',
  tradePartnerGst: 'feisty',
  anchorTraderGSTComplianceScore: 'or strawberry',
  sellerTradePerformanceIndex: 'beneath',
};

export const sampleWithFullData: IRequestOffer = {
  id: 28382,
  reqOffUlidId: 'censure oof',
  reqOfferRefNo: 'since',
  offerValue: 31221,
  requestAmt: 3795,
  tradeValue: 13314,
  marginPtg: 20940.98,
  marginValue: 31742,
  amountAftMargin: 25387,
  interestPtg: 28564.48,
  term: 1694,
  interestValue: 29280.08,
  netAmount: 19507,
  status: 'near latte necklace',
  financeRequestDate: dayjs('2024-03-20'),
  anchorTraderName: 'huzzah unknown vaguely',
  tradePartnerName: 'panic whose',
  anchorTraderGst: 'pfft pish lively',
  tradePartnerGst: 'reservoir sour redefine',
  anchorTraderGSTComplianceScore: 'accouter cruelly',
  anchorTraderGSTERPPeerScore: 'whoa physics till',
  sellerTradePerformanceIndex: 'or secondary grab-bag',
};

export const sampleWithNewData: NewRequestOffer = {
  offerValue: 6603,
  requestAmt: 29772,
  tradeValue: 20738,
  marginPtg: 7934.59,
  marginValue: 29856,
  amountAftMargin: 30954,
  interestPtg: 29520.04,
  term: 14560,
  interestValue: 22858.05,
  netAmount: 27851,
  status: 'examination yippee and',
  financeRequestDate: dayjs('2024-03-20'),
  anchorTraderName: 'until',
  tradePartnerName: 'athwart why',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
