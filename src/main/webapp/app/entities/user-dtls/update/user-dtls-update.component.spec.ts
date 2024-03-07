import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserDtlsService } from '../service/user-dtls.service';
import { IUserDtls } from '../user-dtls.model';
import { UserDtlsFormService } from './user-dtls-form.service';

import { UserDtlsUpdateComponent } from './user-dtls-update.component';

describe('UserDtls Management Update Component', () => {
  let comp: UserDtlsUpdateComponent;
  let fixture: ComponentFixture<UserDtlsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userDtlsFormService: UserDtlsFormService;
  let userDtlsService: UserDtlsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), UserDtlsUpdateComponent],
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
      .overrideTemplate(UserDtlsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserDtlsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userDtlsFormService = TestBed.inject(UserDtlsFormService);
    userDtlsService = TestBed.inject(UserDtlsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const userDtls: IUserDtls = { id: 456 };

      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      expect(comp.userDtls).toEqual(userDtls);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsFormService, 'getUserDtls').mockReturnValue(userDtls);
      jest.spyOn(userDtlsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userDtls }));
      saveSubject.complete();

      // THEN
      expect(userDtlsFormService.getUserDtls).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(userDtlsService.update).toHaveBeenCalledWith(expect.objectContaining(userDtls));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsFormService, 'getUserDtls').mockReturnValue({ id: null });
      jest.spyOn(userDtlsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userDtls }));
      saveSubject.complete();

      // THEN
      expect(userDtlsFormService.getUserDtls).toHaveBeenCalled();
      expect(userDtlsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IUserDtls>>();
      const userDtls = { id: 123 };
      jest.spyOn(userDtlsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userDtls });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userDtlsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
