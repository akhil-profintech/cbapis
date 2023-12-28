import dayjs from 'dayjs/esm';

import { IRequestOffer, NewRequestOffer } from './request-offer.model';

export const sampleWithRequiredData: IRequestOffer = {
  id: 31781,
  offerValue: 18723,
  requestAmt: 22224,
  tradeValue: 12691,
  marginPtg: 13784.28,
  marginValue: 29239,
  amountAftMargin: 1566,
  interestPtg: 32497.98,
  term: 18553,
  interestValue: 23332.78,
  netAmount: 22067,
  status: 'defiantly',
  financeRequestDate: dayjs('2023-12-27'),
  anchorTraderName: 'why until airfreight',
  tradePartnerName: 'gather',
};

export const sampleWithPartialData: IRequestOffer = {
  id: 12256,
  requestOfferRefNo: 'about over cell',
  offerValue: 28511,
  requestAmt: 27282,
  tradeValue: 29647,
  marginPtg: 7581.72,
  marginValue: 26657,
  amountAftMargin: 28511,
  interestPtg: 19061.42,
  term: 25454,
  interestValue: 31931.51,
  netAmount: 19589,
  status: 'underneath over',
  financeRequestDate: dayjs('2023-12-28'),
  anchorTraderName: 'aboard wherever',
  tradePartnerName: 'jungle',
  anchorTraderGst: 'shareholder',
  tradePartnerGst: 'cheerfully searchingly provided',
  buyerName: 'while',
};

export const sampleWithFullData: IRequestOffer = {
  id: 29268,
  reqOffId: 'helplessly whoa usefully',
  requestOfferRefNo: 'dresser imprint',
  offerValue: 16399,
  requestAmt: 12236,
  tradeValue: 29813,
  marginPtg: 24220.19,
  marginValue: 26292,
  amountAftMargin: 4464,
  interestPtg: 21024.49,
  term: 18364,
  interestValue: 9407.26,
  netAmount: 31065,
  status: 'marketing',
  financeRequestDate: dayjs('2023-12-28'),
  anchorTraderName: 'crisp frequent',
  tradePartnerName: 'concerning',
  anchorTraderGst: 'viciously above',
  tradePartnerGst: 'plain athwart',
  sellerName: 'gopher',
  buyerName: 'happily hopelessly bonding',
  anchorTraderGstComplianceScore: 'picket',
  anchorTraderErpPeerScore: 'that justly',
};

export const sampleWithNewData: NewRequestOffer = {
  offerValue: 20430,
  requestAmt: 27244,
  tradeValue: 22721,
  marginPtg: 31868.7,
  marginValue: 17777,
  amountAftMargin: 7950,
  interestPtg: 26451.63,
  term: 4460,
  interestValue: 16422.86,
  netAmount: 17311,
  status: 'baggy',
  financeRequestDate: dayjs('2023-12-28'),
  anchorTraderName: 'that third easy-going',
  tradePartnerName: 'yippee',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
