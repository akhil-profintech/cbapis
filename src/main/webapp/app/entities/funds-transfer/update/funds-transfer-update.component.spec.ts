import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';
import { TradeEntityService } from 'app/entities/trade-entity/service/trade-entity.service';
import { FundsTransferService } from '../service/funds-transfer.service';
import { IFundsTransfer } from '../funds-transfer.model';
import { FundsTransferFormService } from './funds-transfer-form.service';

import { FundsTransferUpdateComponent } from './funds-transfer-update.component';

describe('FundsTransfer Management Update Component', () => {
  let comp: FundsTransferUpdateComponent;
  let fixture: ComponentFixture<FundsTransferUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let fundsTransferFormService: FundsTransferFormService;
  let fundsTransferService: FundsTransferService;
  let tradeEntityService: TradeEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FundsTransferUpdateComponent],
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
      .overrideTemplate(FundsTransferUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FundsTransferUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fundsTransferFormService = TestBed.inject(FundsTransferFormService);
    fundsTransferService = TestBed.inject(FundsTransferService);
    tradeEntityService = TestBed.inject(TradeEntityService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call TradeEntity query and add missing value', () => {
      const fundsTransfer: IFundsTransfer = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 10710 };
      fundsTransfer.tradeEntity = tradeEntity;

      const tradeEntityCollection: ITradeEntity[] = [{ id: 26158 }];
      jest.spyOn(tradeEntityService, 'query').mockReturnValue(of(new HttpResponse({ body: tradeEntityCollection })));
      const additionalTradeEntities = [tradeEntity];
      const expectedCollection: ITradeEntity[] = [...additionalTradeEntities, ...tradeEntityCollection];
      jest.spyOn(tradeEntityService, 'addTradeEntityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ fundsTransfer });
      comp.ngOnInit();

      expect(tradeEntityService.query).toHaveBeenCalled();
      expect(tradeEntityService.addTradeEntityToCollectionIfMissing).toHaveBeenCalledWith(
        tradeEntityCollection,
        ...additionalTradeEntities.map(expect.objectContaining),
      );
      expect(comp.tradeEntitiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const fundsTransfer: IFundsTransfer = { id: 456 };
      const tradeEntity: ITradeEntity = { id: 2999 };
      fundsTransfer.tradeEntity = tradeEntity;

      activatedRoute.data = of({ fundsTransfer });
      comp.ngOnInit();

      expect(comp.tradeEntitiesSharedCollection).toContain(tradeEntity);
      expect(comp.fundsTransfer).toEqual(fundsTransfer);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransfer>>();
      const fundsTransfer = { id: 123 };
      jest.spyOn(fundsTransferFormService, 'getFundsTransfer').mockReturnValue(fundsTransfer);
      jest.spyOn(fundsTransferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransfer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fundsTransfer }));
      saveSubject.complete();

      // THEN
      expect(fundsTransferFormService.getFundsTransfer).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(fundsTransferService.update).toHaveBeenCalledWith(expect.objectContaining(fundsTransfer));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransfer>>();
      const fundsTransfer = { id: 123 };
      jest.spyOn(fundsTransferFormService, 'getFundsTransfer').mockReturnValue({ id: null });
      jest.spyOn(fundsTransferService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransfer: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: fundsTransfer }));
      saveSubject.complete();

      // THEN
      expect(fundsTransferFormService.getFundsTransfer).toHaveBeenCalled();
      expect(fundsTransferService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFundsTransfer>>();
      const fundsTransfer = { id: 123 };
      jest.spyOn(fundsTransferService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ fundsTransfer });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(fundsTransferService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareTradeEntity', () => {
      it('Should forward to tradeEntityService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(tradeEntityService, 'compareTradeEntity');
        comp.compareTradeEntity(entity, entity2);
        expect(tradeEntityService.compareTradeEntity).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
