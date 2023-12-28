import { IParticipantSettlement, NewParticipantSettlement } from './participant-settlement.model';

export const sampleWithRequiredData: IParticipantSettlement = {
  id: 30996,
  participantSettlementId: 31289,
  participantId: 18293,
  participantName: 'qua',
  gstId: 'suddenly despite',
  settlementType: 'catacomb',
  settlementAmount: 27211,
  settlementDate: 'sometimes',
  settlementDueDate: 'bad upon gift',
  settlementContactInfo: 'phooey',
  patronOfPayment: 'after antagonize',
  recipientOfPayment: 'recreation present',
  settlementMethod: 'what feather stoke',
  tenantId: 32068,
  accName: 'quietly',
  ifscCode: 'like suppression where',
  accNumber: 8374,
  docId: 'bah',
};

export const sampleWithPartialData: IParticipantSettlement = {
  id: 32140,
  participantSettlementId: 32162,
  participantId: 3883,
  participantName: 'apprise naive whose',
  gstId: 'internal plagiarism through',
  settlementType: 'stump elegantly obsession',
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
  participantId: 24147,
  participantName: 'save',
  gstId: 'whoa previous until',
  settlementType: 'critique fondly gratefully',
  settlementAmount: 15299,
  settlementDate: 'acidly',
  settlementDueDate: 'brr taut gently',
  settlementContactInfo: 'limply lively drag',
  patronOfPayment: 'vainly robin',
  recipientOfPayment: 'oust',
  settlementMethod: 'qua after',
  tenantId: 2316,
  accName: 'validity practical through',
  ifscCode: 'that blissfully',
  accNumber: 10843,
  docId: 'pillory',
};

export const sampleWithNewData: NewParticipantSettlement = {
  participantSettlementId: 1396,
  participantId: 12747,
  participantName: 'because when powerfully',
  gstId: 'drat without incidentally',
  settlementType: 'too',
  settlementAmount: 14566,
  settlementDate: 'bravely mouth',
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
