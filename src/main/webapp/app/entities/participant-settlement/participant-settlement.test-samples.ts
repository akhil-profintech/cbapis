import { IParticipantSettlement, NewParticipantSettlement } from './participant-settlement.model';

export const sampleWithRequiredData: IParticipantSettlement = {
  id: 25263,
  participantId: 8286,
  participantName: 'embassy silkworm hollow',
  gstId: 'fooey',
  settlementType: 'noisily reminder',
  settlementAmount: 15671,
  settlementDate: 'even ceiling',
  settlementDueDate: 'as yahoo flower',
  settlementContactInfo: 'psst threadbare',
  patronOfPayment: 'promptly',
  recipientOfPayment: 'bravely provided',
  settlementMethod: 'whereas',
  tenantId: 24778,
  accName: 'thicken impostor',
  ifscCode: 'at which',
  accNumber: 1865,
  docId: 'unethically apprise',
};

export const sampleWithPartialData: IParticipantSettlement = {
  id: 13195,
  participantSettlementId: 8896,
  participantSettlementUlidId: 'fervently instead',
  participantId: 29853,
  participantName: 'researches wreak provided',
  gstId: 'carbohydrate',
  settlementType: 'obsession',
  settlementAmount: 30033,
  settlementDate: 'plus',
  settlementDueDate: 'yawn bandage pro',
  settlementContactInfo: 'restfully yowza although',
  patronOfPayment: 'even task psst',
  recipientOfPayment: 'past beside',
  settlementMethod: 'prudent moody',
  tenantId: 25494,
  accName: 'than once',
  ifscCode: 'serial raspberry',
  accNumber: 5716,
  docId: 'defiantly blather',
};

export const sampleWithFullData: IParticipantSettlement = {
  id: 12926,
  participantSettlementId: 3895,
  participantSettlementUlidId: 'politely till round',
  participantId: 24602,
  participantName: 'until generous',
  gstId: 'fondly',
  settlementType: 'for',
  settlementAmount: 133,
  settlementDate: 'tomorrow brr',
  settlementDueDate: 'cheap',
  settlementContactInfo: 'now ouch cabana',
  patronOfPayment: 'plus so',
  recipientOfPayment: 'knavishly psst',
  settlementMethod: 'made-up afore',
  tenantId: 31956,
  accName: 'which uncommon premiere',
  ifscCode: 'gadzooks exult tough',
  accNumber: 20439,
  docId: 'elastic',
};

export const sampleWithNewData: NewParticipantSettlement = {
  participantId: 26410,
  participantName: 'when',
  gstId: 'till',
  settlementType: 'why outstrip displease',
  settlementAmount: 32334,
  settlementDate: 'interestingly bravely mouth',
  settlementDueDate: 'except rostrum pish',
  settlementContactInfo: 'decent oof',
  patronOfPayment: 'indeed',
  recipientOfPayment: 'scourge caper',
  settlementMethod: 'but gainsay',
  tenantId: 30281,
  accName: 'affirm',
  ifscCode: 'yearningly faithfully scarcely',
  accNumber: 2473,
  docId: 'until',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
