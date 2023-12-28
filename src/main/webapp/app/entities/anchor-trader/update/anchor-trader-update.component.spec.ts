import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AnchorTraderService } from '../service/anchor-trader.service';
import { IAnchorTrader } from '../anchor-trader.model';
import { AnchorTraderFormService } from './anchor-trader-form.service';

import { AnchorTraderUpdateComponent } from './anchor-trader-update.component';

describe('AnchorTrader Management Update Component', () => {
  let comp: AnchorTraderUpdateComponent;
  let fixture: ComponentFixture<AnchorTraderUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let anchorTraderFormService: AnchorTraderFormService;
  let anchorTraderService: AnchorTraderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AnchorTraderUpdateComponent],
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
      .overrideTemplate(AnchorTraderUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AnchorTraderUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    anchorTraderFormService = TestBed.inject(AnchorTraderFormService);
    anchorTraderService = TestBed.inject(AnchorTraderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const anchorTrader: IAnchorTrader = { id: 456 };

      activatedRoute.data = of({ anchorTrader });
      comp.ngOnInit();

      expect(comp.anchorTrader).toEqual(anchorTrader);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTrader>>();
      const anchorTrader = { id: 123 };
      jest.spyOn(anchorTraderFormService, 'getAnchorTrader').mockReturnValue(anchorTrader);
      jest.spyOn(anchorTraderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTrader });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: anchorTrader }));
      saveSubject.complete();

      // THEN
      expect(anchorTraderFormService.getAnchorTrader).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(anchorTraderService.update).toHaveBeenCalledWith(expect.objectContaining(anchorTrader));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTrader>>();
      const anchorTrader = { id: 123 };
      jest.spyOn(anchorTraderFormService, 'getAnchorTrader').mockReturnValue({ id: null });
      jest.spyOn(anchorTraderService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTrader: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: anchorTrader }));
      saveSubject.complete();

      // THEN
      expect(anchorTraderFormService.getAnchorTrader).toHaveBeenCalled();
      expect(anchorTraderService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAnchorTrader>>();
      const anchorTrader = { id: 123 };
      jest.spyOn(anchorTraderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ anchorTrader });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(anchorTraderService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
