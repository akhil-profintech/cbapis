import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IAnchorTrader } from 'app/entities/anchor-trader/anchor-trader.model';
import { AnchorTraderService } from 'app/entities/anchor-trader/service/anchor-trader.service';
import { AnchorTraderPartnerService } from '../service/anchor-trader-partner.service';
import { IAnchorTraderPartner } from '../anchor-trader-partner.model';
import { AnchorTraderPartnerFormService } from './anchor-trader-partner-form.service';

import { AnchorTraderPartnerUpdateComponent } from './anchor-trader-partner-update.component';

describe('AnchorTraderPartner Management Update Component', () => {
  let comp: AnchorTraderPartnerUpdateComponent;
  let fixture: ComponentFixture<AnchorTraderPartnerUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let anchorTraderPartnerFormService: AnchorTraderPartnerFormService;
  let anchorTraderPartnerService: AnchorTraderPartnerService;
  let anchorTraderService: AnchorTraderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AnchorTraderPartnerUpdateComponent],
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
      .overrideTemplate(AnchorTraderPartnerUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnchorTraderPartnerUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    anchorTraderPartnerFormService = TestBed.inject(AnchorTraderPartnerFormService);
    anchorTraderPartnerService = TestBed.inject(AnchorTraderPartnerService);
    anchorTraderService = TestBed.inject(AnchorTraderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call AnchorTrader query and add missing value', () => {
      const anchorTraderPartner: IAnchorTraderPartner = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 339 };
      anchorTraderPartner.anchortrader = anchortrader;

      const anchorTraderCollection: IAnchorTrader[] = [{ id: 2966 }];
      jest.spyOn(anchorTraderService, 'query').mockReturnValue(of(new HttpResponse({ body: anchorTraderCollection })));
      const additionalAnchorTraders = [anchortrader];
      const expectedCollection: IAnchorTrader[] = [...additionalAnchorTraders, ...anchorTraderCollection];
      jest.spyOn(anchorTraderService, 'addAnchorTraderToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ anchorTraderPartner });
      comp.ngOnInit();

      expect(anchorTraderService.query).toHaveBeenCalled();
      expect(anchorTraderService.addAnchorTraderToCollectionIfMissing).toHaveBeenCalledWith(
        anchorTraderCollection,
        ...additionalAnchorTraders.map(expect.objectContaining),
      );
      expect(comp.anchorTradersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const anchorTraderPartner: IAnchorTraderPartner = { id: 456 };
      const anchortrader: IAnchorTrader = { id: 24290 };
      anchorTraderPartner.anchortrader = anchortrader;

      activatedRoute.data = of({ anchorTraderPartner });
      comp.ngOnInit();

      expect(comp.anchorTradersSharedCollection).toContain(anchortrader);
      expect(comp.anchorTraderPartner).toEqual(anchorTraderPartner);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTraderPartner>>();
      const anchorTraderPartner = { id: 123 };
      jest.spyOn(anchorTraderPartnerFormService, 'getAnchorTraderPartner').mockReturnValue(anchorTraderPartner);
      jest.spyOn(anchorTraderPartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTraderPartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: anchorTraderPartner }));
      saveSubject.complete();

      // THEN
      expect(anchorTraderPartnerFormService.getAnchorTraderPartner).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(anchorTraderPartnerService.update).toHaveBeenCalledWith(expect.objectContaining(anchorTraderPartner));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTraderPartner>>();
      const anchorTraderPartner = { id: 123 };
      jest.spyOn(anchorTraderPartnerFormService, 'getAnchorTraderPartner').mockReturnValue({ id: null });
      jest.spyOn(anchorTraderPartnerService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTraderPartner: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: anchorTraderPartner }));
      saveSubject.complete();

      // THEN
      expect(anchorTraderPartnerFormService.getAnchorTraderPartner).toHaveBeenCalled();
      expect(anchorTraderPartnerService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTraderPartner>>();
      const anchorTraderPartner = { id: 123 };
      jest.spyOn(anchorTraderPartnerService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTraderPartner });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(anchorTraderPartnerService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAnchorTrader', () => {
      it('Should forward to anchorTraderService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(anchorTraderService, 'compareAnchorTrader');
        comp.compareAnchorTrader(entity, entity2);
        expect(anchorTraderService.compareAnchorTrader).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
