import dayjs from 'dayjs/esm';

import { ITrade, NewTrade } from './trade.model';

export const sampleWithRequiredData: ITrade = {
  id: 7510,
};

export const sampleWithPartialData: ITrade = {
  id: 21407,
  tradeRefNo: 'position sympathetic',
  tradeAmount: 'as whoa',
  invoiceNumber: 'extra-small forenenst boo',
  tradeDocType: 'mosh accurate',
  tradeMilestoneStatus: 'before unsightly',
  tradeAdvancePayment: 'lottery indeed',
  anchorTraderName: 'drafty vainly',
  invoiceTerm: 10278,
  reasonForFinance: 'gah',
};

export const sampleWithFullData: ITrade = {
  id: 29430,
  tradeUlidId: 'knottily forenenst',
  tradeRefNo: 'divest oh phooey',
  sellerGstId: 'ugh',
  buyerGstId: 'once',
  tradeAmount: 'obedient',
  invoiceDate: dayjs('2024-03-27'),
  invoiceNumber: 'out anchored',
  tradeDocType: 'hence',
  tradeDocSource: 'towards',
  tradeDocCredibility: 'honestly wretched er',
  tradeMilestoneStatus: 'fortunately zowie garment',
  tradeAdvancePayment: 'beside junior ruddy',
  anchorTraderName: 'spherical meh however',
  tradePartnerName: 'annually sherbet',
  invoiceTerm: 19518,
  acceptanceFromTradePartner: 'limited keenly finally',
  reasonForFinance: 'reconvene tight minty',
  tradePartnerSector: 'tangle',
  tradePartnerLocation: 'tab till',
  tradePartnerGstComplianceScore: 'eminent',
};

export const sampleWithNewData: NewTrade = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
