import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UserDtlsDetailComponent } from './user-dtls-detail.component';

describe('UserDtls Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserDtlsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: UserDtlsDetailComponent,
              resolve: { userDtls: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(UserDtlsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load userDtls on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', UserDtlsDetailComponent);

      // THEN
      expect(instance.userDtls).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
