FROM node:20 as build-stage

WORKDIR /app

COPY package*.json ./

RUN npm install --legacy-peer-deps --force

COPY . .

RUN npm run build --prod

FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build-stage /app/dist/demo-ui /usr/share/nginx/html

EXPOSE 80
