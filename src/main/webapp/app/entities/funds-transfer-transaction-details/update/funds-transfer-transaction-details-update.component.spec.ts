import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { ParticipantSettlementService } from 'app/entities/participant-settlement/service/participant-settlement.service';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';
import { FundsTransferTransactionDetailsFormService } from './funds-transfer-transaction-details-form.service';

import { FundsTransferTransactionDetailsUpdateComponent } from './funds-transfer-transaction-details-update.component';

describe('FundsTransferTransactionDetails Management Update Component', () => {
  let comp: FundsTransferTransactionDetailsUpdateComponent;
  let fixture: ComponentFixture<FundsTransferTransactionDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fundsTransferTransactionDetailsFormService: FundsTransferTransactionDetailsFormService;
  let fundsTransferTransactionDetailsService: FundsTransferTransactionDetailsService;
  let participantSettlementService: ParticipantSettlementService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FundsTransferTransactionDetailsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(FundsTransferTransactionDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FundsTransferTransactionDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fundsTransferTransactionDetailsFormService = TestBed.inject(FundsTransferTransactionDetailsFormService);
    fundsTransferTransactionDetailsService = TestBed.inject(FundsTransferTransactionDetailsService);
    participantSettlementService = TestBed.inject(ParticipantSettlementService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call ParticipantSettlement query and add missing value', () => {
      const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = { id: 456 };
      const participantsettlement: IParticipantSettlement = { id: 27864 };
      fundsTransferTransactionDetails.participantsettlement = participantsettlement;

      const participantSettlementCollection: IParticipantSettlement[] = [{ id: 25263 }];
      jest.spyOn(participantSettlementService, 'query').mockReturnValue(of(new HttpResponse({ body: participantSettlementCollection })));
      const additionalParticipantSettlements = [participantsettlement];
      const expectedCollection: IParticipantSettlement[] = [...additionalParticipantSettlements, ...participantSettlementCollection];
      jest.spyOn(participantSettlementService, 'addParticipantSettlementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      expect(participantSettlementService.query).toHaveBeenCalled();
      expect(participantSettlementService.addParticipantSettlementToCollectionIfMissing).toHaveBeenCalledWith(
        participantSettlementCollection,
        ...additionalParticipantSettlements.map(expect.objectContaining),
      );
      expect(comp.participantSettlementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Disbursement query and add missing value', () => {
      const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 31647 };
      fundsTransferTransactionDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 12451 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = { id: 456 };
      const repayment: IRepayment = { id: 25166 };
      fundsTransferTransactionDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 30490 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = { id: 456 };
      const participantsettlement: IParticipantSettlement = { id: 8286 };
      fundsTransferTransactionDetails.participantsettlement = participantsettlement;
      const disbursement: IDisbursement = { id: 4432 };
      fundsTransferTransactionDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 7950 };
      fundsTransferTransactionDetails.repayment = repayment;

      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      expect(comp.participantSettlementsSharedCollection).toContain(participantsettlement);
      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.fundsTransferTransactionDetails).toEqual(fundsTransferTransactionDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransferTransactionDetails>>();
      const fundsTransferTransactionDetails = { id: 123 };
      jest
        .spyOn(fundsTransferTransactionDetailsFormService, 'getFundsTransferTransactionDetails')
        .mockReturnValue(fundsTransferTransactionDetails);
      jest.spyOn(fundsTransferTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fundsTransferTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(fundsTransferTransactionDetailsFormService.getFundsTransferTransactionDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(fundsTransferTransactionDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(fundsTransferTransactionDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransferTransactionDetails>>();
      const fundsTransferTransactionDetails = { id: 123 };
      jest.spyOn(fundsTransferTransactionDetailsFormService, 'getFundsTransferTransactionDetails').mockReturnValue({ id: null });
      jest.spyOn(fundsTransferTransactionDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransferTransactionDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fundsTransferTransactionDetails }));
      saveSubject.complete();

      // THEN
      expect(fundsTransferTransactionDetailsFormService.getFundsTransferTransactionDetails).toHaveBeenCalled();
      expect(fundsTransferTransactionDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransferTransactionDetails>>();
      const fundsTransferTransactionDetails = { id: 123 };
      jest.spyOn(fundsTransferTransactionDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransferTransactionDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fundsTransferTransactionDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareParticipantSettlement', () => {
      it('Should forward to participantSettlementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(participantSettlementService, 'compareParticipantSettlement');
        comp.compareParticipantSettlement(entity, entity2);
        expect(participantSettlementService.compareParticipantSettlement).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareDisbursement', () => {
      it('Should forward to disbursementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(disbursementService, 'compareDisbursement');
        comp.compareDisbursement(entity, entity2);
        expect(disbursementService.compareDisbursement).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRepayment', () => {
      it('Should forward to repaymentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(repaymentService, 'compareRepayment');
        comp.compareRepayment(entity, entity2);
        expect(repaymentService.compareRepayment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
