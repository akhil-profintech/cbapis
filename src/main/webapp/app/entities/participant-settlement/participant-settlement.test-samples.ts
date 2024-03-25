import { IParticipantSettlement, NewParticipantSettlement } from './participant-settlement.model';

export const sampleWithRequiredData: IParticipantSettlement = {
  id: 25263,
  participantId: 8286,
  participantName: 'embassy silkworm hollow',
  gstId: 'fooey',
  settlementType: 'TPSettlement',
  settlementAmount: 31685,
  settlementDate: 'cudgel franchise even',
  settlementDueDate: 'smoothly as yahoo',
  settlementContactInfo: 'incidentally psst',
  patronOfPayment: 'frail',
  recipientOfPayment: 'than bravely',
  settlementMethod: 'blah whereas troll',
  tenantId: 15148,
  accName: 'impostor unless yacht',
  ifscCode: 'um whimper indeed',
  accNumber: 21657,
  docId: 'whose quick',
};

export const sampleWithPartialData: IParticipantSettlement = {
  id: 5956,
  participantSettlementId: 21611,
  participantSettlementUlidId: 'likewise',
  participantId: 22797,
  participantName: 'provided almond chauffeur',
  gstId: 'back healthily',
  settlementType: 'TPSettlement',
  settlementAmount: 10071,
  settlementDate: 'promptly aha oh',
  settlementDueDate: 'that',
  settlementContactInfo: 'although',
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
  settlementType: 'ATSettlement',
  settlementAmount: 3231,
  settlementDate: 'concerning tomorrow brr',
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
  settlementType: 'PFTSettlement',
  settlementAmount: 15864,
  settlementDate: 'crisis untidy result',
  settlementDueDate: 'wherever calmly truthful',
  settlementContactInfo: 'furthermore ambassador',
  patronOfPayment: 'drat decontrol cruel',
  recipientOfPayment: 'since scourge',
  settlementMethod: 'motel',
  tenantId: 6572,
  accName: 'zowie',
  ifscCode: 'ouch',
  accNumber: 24523,
  docId: 'obnoxiously',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
