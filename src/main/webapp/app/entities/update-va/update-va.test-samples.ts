import { IUpdateVA, NewUpdateVA } from './update-va.model';

export const sampleWithRequiredData: IUpdateVA = {
  id: 2880,
  updateVirAccId: 8886,
  requestDateTime: 'roost planter',
  responseDateTime: 'where assurance hm',
  lastupdatedDateTime: 'an as',
  lastUpdatedBy: 'lazy to',
};

export const sampleWithPartialData: IUpdateVA = {
  id: 16,
  updateVirAccId: 30834,
  subGrpId: 'whoa blah',
  bizObjId: 'considering desensitise after',
  timestamp: 'skin site psst',
  requestDateTime: 'segue inside cleverly',
  reslt: 'ferociously',
  status: 'rapid',
  statusDesc: 'honestly',
  responseDateTime: 'untidy uh-huh',
  lastupdatedDateTime: 'zowie equally',
  lastUpdatedBy: 'until psst',
};

export const sampleWithFullData: IUpdateVA = {
  id: 8699,
  updateVirAccId: 31864,
  finReqId: 'impure instead before',
  subGrpId: 'regret',
  msgId: 'poorly immediately',
  cnvId: 'miter gun',
  extRefId: 'scrummage meh',
  bizObjId: 'droopy detection',
  appId: 'prioritize tug',
  timestamp: 'very past',
  vaNum: 'ideal',
  finPar: 'once',
  clientCode: 'against loftily ack',
  requestDateTime: 'innocence',
  reslt: 'somewhere modulo ick',
  status: 'doll scam',
  statusDesc: 'oh loyalty min',
  errorCode: 'mismatch fooey or',
  responseDateTime: 'timeline console',
  lastupdatedDateTime: 'instead',
  lastUpdatedBy: 'an even questionably',
};

export const sampleWithNewData: NewUpdateVA = {
  updateVirAccId: 6297,
  requestDateTime: 'tired',
  responseDateTime: 'whether anklet',
  lastupdatedDateTime: 'valiantly valet homeland',
  lastUpdatedBy: 'meh',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
