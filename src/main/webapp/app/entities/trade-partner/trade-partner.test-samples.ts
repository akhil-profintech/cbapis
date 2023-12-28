import { ITradePartner, NewTradePartner } from './trade-partner.model';

export const sampleWithRequiredData: ITradePartner = {
  id: 21813,
  tenantId: 'blank instructive psst',
  tpId: 'mysteriously',
  orgId: 'er slim',
  customerName: 'notarize meanwhile successfully',
  orgName: 'sponge',
  gstId: 'opposite zowie',
  phoneNumber: 31997,
};

export const sampleWithPartialData: ITradePartner = {
  id: 28574,
  tenantId: 'word small alarmed',
  tpId: 'skipper',
  orgId: 'oof ride gently',
  customerName: 'sweatshop meh parched',
  orgName: 'whoever',
  gstId: 'notwithstanding diluted despite',
  phoneNumber: 21406,
  tradePartnerName: 'ignore propagate',
  location: 'jell deceivingly if',
  tradepartnerGST: 'order insomnia',
  tradePartnerSector: 'eek lest',
};

export const sampleWithFullData: ITradePartner = {
  id: 24682,
  tenantId: 'punctually off',
  tpId: 'astride for',
  orgId: 'urge meh',
  customerName: 'how access feline',
  orgName: 'past bashfully urban',
  gstId: 'hourly',
  phoneNumber: 7907,
  tradePartnerName: 'repeatedly but hmph',
  location: 'variety',
  tradepartnerGST: 'ornery',
  tradePartnerSector: 'acidly how inwardly',
  acceptanceFromTradePartner: 'merrily coordinated',
};

export const sampleWithNewData: NewTradePartner = {
  tenantId: 'within',
  tpId: 'after',
  orgId: 'humongous final',
  customerName: 'similar truly mercerise',
  orgName: 'briefly',
  gstId: 'minus what',
  phoneNumber: 11175,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
